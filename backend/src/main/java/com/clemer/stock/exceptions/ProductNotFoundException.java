package com.clemer.stock.exceptions;

import com.clemer.stock.utils.Messages;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super(Messages.PRODUCT_NOT_FOUND);
    }
}
