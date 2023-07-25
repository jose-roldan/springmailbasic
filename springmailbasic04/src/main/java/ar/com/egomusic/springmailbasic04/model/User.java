package ar.com.egomusic.springmailbasic04.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author jose.roldan
 */
@Data
@ToString
@AllArgsConstructor
public class User {

    private String userName;
    private String emailAddress;

}
