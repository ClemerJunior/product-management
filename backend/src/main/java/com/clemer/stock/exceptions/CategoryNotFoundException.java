package com.clemer.stock.exceptions;

import com.clemer.stock.utils.Messages;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super(Messages.CATEGORY_NOT_FOUND);
    }
}
