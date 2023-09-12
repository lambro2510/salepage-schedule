package com.salespage.salepageschedule.domains.services;

import com.salespage.salepageschedule.domains.entities.ProductTransaction;
import com.salespage.salepageschedule.domains.entities.Shipper;
import com.salespage.salepageschedule.domains.entities.types.ProductTransactionState;
import com.salespage.salepageschedule.domains.entities.types.VehicleType;
import com.salespage.salepageschedule.domains.info.DistanceMatrixResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipperService extends BaseService{

  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public void findShipperForProduct() {
    List<Shipper> freeShippers = shipperStorage.findByShipModeAndAcceptTransaction(true, false);
    List<ProductTransaction> productTransactions = productTransactionStorage.findProductTransactionByState(ProductTransactionState.ACCEPT_STORE);
    for(Shipper shipper : freeShippers){
      for(ProductTransaction productTransaction: productTransactions){
        String shipperLocation = shipper.getLatitude() + ',' + shipper.getLongitude();
        DistanceMatrixResult.Distance distance = getDistance(shipperLocation, productTransaction.getStore().getLocation(), shipper.getVehicleType().getValue());
        if(VehicleType.CAR == shipper.getVehicleType()){
          if(distance.getValue() < 10000){
            shipper.setAcceptTransaction(true);
            productTransaction.setState(ProductTransactionState.WAITING_SHIPPER);
          }
        }
      }
    }
    shipperStorage.saveAll(freeShippers);
    productTransactionStorage.saveAll(productTransactions);
  }

}
