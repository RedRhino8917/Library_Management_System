package org.gfg.minor1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TxnCreateRequest {

    @NotBlank(message = "book No should not be blank")
    private String bookNo;

    @Positive(message = "amount should be positive")
    private Integer amount;
}
