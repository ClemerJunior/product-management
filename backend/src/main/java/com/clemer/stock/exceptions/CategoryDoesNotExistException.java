package com.clemer.stock.exceptions;

import com.clemer.stock.utils.Messages;

public class CategoryDoesNotExistException extends RuntimeException {
    public CategoryDoesNotExistException() {
        super(Messages.CATEGORY_NOT_FOUND);
    }
}
