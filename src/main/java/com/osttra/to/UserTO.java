package com.osttra.to;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTO {
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean approved;
}
