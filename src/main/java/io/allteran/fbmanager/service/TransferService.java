package io.allteran.fbmanager.service;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.domain.Team;
import io.allteran.fbmanager.domain.Transfer;
import io.allteran.fbmanager.exception.CustomException;
import io.allteran.fbmanager.repo.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepo;
    private final PlayerService playerService;

    @Autowired
    public TransferService(TransferRepository transferRepo, PlayerService playerService) {
        this.transferRepo = transferRepo;
        this.playerService = playerService;
    }

    public Transfer transferPlayer(Team seller, Team buyer, Player player) {
        Transfer transfer = new Transfer();
        transfer.setTeamFrom(seller);
        transfer.setTeamTo(buyer);

        double price = player.getExperienceMonth() * 100000 / player.getAgeYear();
        double fee = price * seller.getTransferFeeRate() / 100;
        double totalAmount = price + fee;

        if(totalAmount > buyer.getAccount()) {
            throw new CustomException("Funds on account is not enough");
        } else {
            buyer.setAccount(buyer.getAccount() - totalAmount);
        }

        seller.setAccount(seller.getAccount() + totalAmount);
        transfer.setPrice(price);
        transfer.setTotalAmount(totalAmount);
        transfer.setPlayer(player);
        player.setTeam(buyer);

        playerService.updatePlayer(player, player);
        return transferRepo.save(transfer);
    }

    public List<Transfer> findAll() {
        return transferRepo.findAll();
    }

    public List<Transfer> transferPlayerHistory(Player player) {
        return transferRepo.findAllByPlayer(player);
    }

    public List<Transfer> transferTeamHistory(Team team) {
        return transferRepo.findAllByTeam(team);
    }
}
