package br.com.zup.jocivaldias.transaction.dto.response;

public class TransactionStoreResponse {

    private String name;
    private String city;
    private String address;

    public TransactionStoreResponse(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
