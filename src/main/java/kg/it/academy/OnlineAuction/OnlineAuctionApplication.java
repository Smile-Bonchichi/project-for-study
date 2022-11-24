package kg.it.academy.OnlineAuction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineAuctionApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OnlineAuctionApplication.class);
        app.setBanner((environment, sourceClass, out) -> out.println(
                "╔═══╗──╔╗───────╔═══╗─────╔╗\n" +
                "║╔═╗║──║║───────║╔═╗║────╔╝╚╗\n" +
                "║║─║╠═╗║║╔╦═╗╔══╣║─║╠╗╔╦═╩╗╔╬╦══╦═╗\n" +
                "║║─║║╔╗╣║╠╣╔╗╣║═╣╚═╝║║║║╔═╣║╠╣╔╗║╔╗╗\n" +
                "║╚═╝║║║║╚╣║║║║║═╣╔═╗║╚╝║╚═╣╚╣║╚╝║║║║\n" +
                "╚═══╩╝╚╩═╩╩╝╚╩══╩╝─╚╩══╩══╩═╩╩══╩╝╚╝"));
        app.run(args);
        //SpringApplication.run(OnlineAuctionApplication.class, args);
    }

}
