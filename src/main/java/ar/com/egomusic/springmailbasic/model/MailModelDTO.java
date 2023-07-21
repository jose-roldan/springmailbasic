package ar.com.egomusic.springmailbasic.model;

import java.util.Map;
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
public class MailModelDTO {

    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;
}
