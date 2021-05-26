package com.cafe.facade.user;

import com.cafe.model.dto.user.WaiterCreatingDto;
import com.cafe.repository.WaiterRepository;
import com.cafe.model.entity.user.Waiter;
import com.cafe.service.email.SendEmail;
import com.cafe.convert.WaiterConverter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserCreatingDtoFacade {

    @Lazy
    private final WaiterRepository mRepository;
    @Lazy
    private final PasswordEncoder mPasswordEncoder;
    /**
     * Must set your credentials(username, password) in application properties to send mails.
     */
    @Lazy
    private final SendEmail mSendEmail;

    public UserCreatingDtoFacade(
            WaiterRepository waiterRepository,
            PasswordEncoder passwordEncoder,
            SendEmail sendEmail
    ) {
        this.mRepository = waiterRepository;
        this.mPasswordEncoder = passwordEncoder;
        this.mSendEmail = sendEmail;
    }

    public Long createWaiter(WaiterCreatingDto waiterCreatingDto) {
        Waiter waiter = WaiterConverter.convertToEntity(waiterCreatingDto);
        String tempPassword = RandomString.make(8);
        waiter.setPassword(mPasswordEncoder.encode(tempPassword));

        mRepository.save(waiter);

        mSendEmail.sendCodeToEmail(waiterCreatingDto.getEmail(),
                String.format("Your temporary password : %s \nYou can change your password any time.", tempPassword), "Cafe");
        return waiter.getId();
    }

}