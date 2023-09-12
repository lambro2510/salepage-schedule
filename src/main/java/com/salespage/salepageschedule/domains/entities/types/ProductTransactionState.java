package com.salespage.salepageschedule.domains.entities.types;

public enum ProductTransactionState {

  WAITING_STORE,

  ACCEPT_STORE,

  WAITING_SHIPPER,

  SHIPPER_PROCESSING,

  SHIPPER_COMPLETE,

  ALL_COMPLETE,

  CANCEL
}
