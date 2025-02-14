package in.gov.abdm.uhi.EUABookingService.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "eua")
@Data
public class Messages {
    @Id
    @Column(name = "content_id")
    private String contentId;
    
    @Column(name = "sender")
    private String sender;
    
    @Column(name = "receiver")
    private String receiver;
    
    @Column(name = "content_value",length = 50000)
    private String contentValue;
    
    @Column(name = "content_type")
    private String contentType;
    
    @Column(name = "content_url",length = 50000)
    private String contentUrl;
    
    @Column(name = "time")
    private LocalDateTime  time;
    
    @Column(name = "consumer_url")
    private String consumerUrl;
    
    @Column(name = "provider_url")
    private String providerUrl;
    
    

}
