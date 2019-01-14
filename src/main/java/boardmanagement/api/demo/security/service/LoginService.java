package boardmanagement.api.demo.security.service;

import boardmanagement.api.demo.common.bean.entity.PasswordEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.repository.AppUserRepository;
import boardmanagement.api.demo.manage.repository.PasswordRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザサービスクラス.
 */
@Service
public class LoginService {

    @NonNull
    private final AppUserRepository appUserRepository;

    @NonNull
    private final PasswordRepository passwordRepository;

    @Autowired
    public LoginService(AppUserRepository appUserRepository, PasswordRepository passwordRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordRepository = passwordRepository;
    }

    /**
     * セッション情報からログインユーザを取得する.
     * ログインユーザが取得できなかった場合、IllegalArgumentExcepiton.
     * セッションにログインユーザが存在するかどうか不透明な場合、存在チェックをかけていることが前提となる.
     *
     * @return ログインユーザ
     */
    public PasswordEntityBean findPasswordByUserId(String userId) {
        Optional<PasswordEntityBean> passwordEntityBean = passwordRepository.findByUserId(userId);

        if(!passwordEntityBean.isPresent()){
            throw new IllegalArgumentException("cant find password");
        }

        return passwordEntityBean.get();
    }

    /**
     * ユーザ数をカウントする.
     * @param username ユーザ名
     * @return ユーザ数
     */
    public int countById(String username) {
        return appUserRepository.countById(username);
    }

    /**
     * ユーザIDからユーザ情報を取得する.
     * @param username ユーザ名
     * @return ユーザ情報
     */
    public Optional<UserEntityBean> findByUserId(String username) {
        return appUserRepository.findById(username).map(UserEntityBean::new);
    }
}
