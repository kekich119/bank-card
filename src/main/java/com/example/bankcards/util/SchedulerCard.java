package com.example.bankcards.util;

import com.example.bankcards.entity.Card;
import com.example.bankcards.service.CardService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SchedulerCard {

    private final CardService cardService;


    public SchedulerCard(CardService cardService) {
        this.cardService = cardService;
    }



    @Scheduled(cron = "@daily")
    public void scheduledCard(){
        List<Card> cards = cardService.findAll();
        for (Card card : cards) {
            if (card.getDateExpire().isBefore(LocalDate.now())) {
                card.setStatus("DEACTIVATED");
                cardService.save(card);
            }
        }






    }
}
