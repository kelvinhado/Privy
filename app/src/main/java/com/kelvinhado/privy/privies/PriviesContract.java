package com.kelvinhado.privy.privies;

import com.kelvinhado.privy.BasePresenter;
import com.kelvinhado.privy.BaseView;
import com.kelvinhado.privy.data.Privy;

import java.util.List;

/**
 * Created by kelvin on 01/10/2017.
 */

public interface PriviesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showPrivies(List<Privy> privies);

        void showFavoritePrivies(List<Privy> privies);

        void showNoPrivies();

        void showLoadingPriviesError();
    }

    interface Presenter extends BasePresenter {

        void loadPrivies();

        void queryFavoritesPrivies();
    }
}
