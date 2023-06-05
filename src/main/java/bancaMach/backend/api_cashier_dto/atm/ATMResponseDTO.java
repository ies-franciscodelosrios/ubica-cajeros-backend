package bancaMach.backend.api_cashier_dto.atm;

public class ATMResponseDTO {
    private int response;
    public ATMResponseDTO(int response) {
        this.response = response;
    }
    public ATMResponseDTO() {}

    public int getResponse() { return response; }

    public void setResponse(int response) { this.response = response; }
}
