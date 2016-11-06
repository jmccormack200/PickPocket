package io.intrepid.pickpocket.classic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClassicPresenterTest {

    @Mock
    ClassicContract.View mockView;

    ClassicPresenter presenter;

    @Before
    public void setup() {
        presenter = new ClassicPresenter();
        presenter.setView(mockView);
    }

    @Test
    public void buttonClickedShouldOpenLockWithCorrectCombination() {
        setupSuccessfulCombinationEntered();

        verify(mockView).unlock();
    }

    @Test
    public void buttonClickedShouldRelockAfterCorrectCombination(){
        // Act
        setupSuccessfulCombinationEntered();

        // Assert
        verify(mockView).unlock();

        // Act
        presenter.buttonClicked("25");

        // Assert
        verify(mockView).lock();
    }

    @Test
    public void buttonClickedShouldUnlockEvenWithJunkDigitsBeforeCorrectCombination(){
        presenter.buttonClicked("25");
        presenter.buttonClicked("25");
        setupSuccessfulCombinationEntered();

        verify(mockView).unlock();
    }

    @Test
    public void buttonClickedShouldOpenLockTwiceIfCorrectCombinationEnteredTwice(){
        setupSuccessfulCombinationEntered();
        setupSuccessfulCombinationEntered();

        verify(mockView, times(2)).unlock();
        verify(mockView).lock();
    }

    @Test
    public void toggleSwitchShouldAllowUserToChangeCombination(){
        // Act
        setupSuccessfulCombinationEntered();

        // Assert
        verify(mockView).unlock();
        reset(mockView);

        // Act
        presenter.programSwitchToggled(true);

        presenter.buttonClicked("1");
        presenter.buttonClicked("1");
        presenter.buttonClicked("1");

        presenter.programSwitchToggled(false);

        // Assert

        verify(mockView).lock();

        // Act

        presenter.buttonClicked("1");
        presenter.buttonClicked("1");
        presenter.buttonClicked("1");

        // Assert

        verify(mockView).unlock();
    }

    @Test
    public void buttonClickedShouldUnlockIfRestartedEnteringTheCorrectPassword(){
        presenter.buttonClicked("1");
        presenter.buttonClicked("2");
        setupSuccessfulCombinationEntered();

        verify(mockView).unlock();
    }

    private void setupSuccessfulCombinationEntered(){
        presenter.buttonClicked("1");
        presenter.buttonClicked("2");
        presenter.buttonClicked("3");
        presenter.buttonClicked("4");
    }
}
