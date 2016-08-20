package com.delicious.delicious.base.repository.observer;


public class TransactionData<T> {
    public T data;
    public boolean isCreated;
    public boolean isUpdated;
    public boolean isDeleted;

    public TransactionData() {
    }

    public TransactionData(T data) {
        this.data = data;
    }

    public static <T> TransactionData<T> create(T data) {
        return new TransactionData<T>(data);
    }

    public TransactionData<T> setUpdated(boolean updated) {
        isUpdated = updated;
        return this;
    }

    public TransactionData<T> setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public TransactionData<T> setCreated(boolean created) {
        isCreated = created;
        return this;
    }
}
