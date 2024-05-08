package org.gfg.minor1.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TxnReturnRequest {
    private String studentContact;
    private String bookNo;
    private String txnId;
}
