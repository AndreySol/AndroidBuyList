package com.buylist.solomakha.buylistapp.presenter;

import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by asolomakha on 3/22/2017.
 */

public class PresenterTest
{
    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Mock
    private BasketListView mBasketListViewMock;

    @Mock
    private BasketListModel mBasketListModelMock;

    private BasketListPresenter mBasketListPresenter;

   /* public PresenterTest(BasketListPresenter mBasketListPresenter)
    {
        this.mBasketListPresenter = mBasketListPresenter;
    }*/


    @Before
    public void setup()
    {
        //mBasketListPresenter = new BasketListPresenterImpl(mBasketListViewMock, mBasketListModelMock);
    }

    @Test
    public void getBasketListTest()
    {
        // setup
        /*List<Basket> basketList = new ArrayList<>();
        basketList.add(new Basket(0, "Test basket 1"));
        basketList.add(new Basket(1, "Test basket 2"));
        basketList.add(new Basket(1, "Test basket 3"));

        when(mBasketListModelMock.getBasketList()).thenReturn(basketList);

        // run
        mBasketListPresenter.loadBasketList();

        // test
        verify(mBasketListViewMock).showBasketList(basketList);

        assertThat(true, not(eq(false)));

        Integer[] ints = new Integer[] { 7, 5, 12, 16 };

        assertThat(ints, arrayWithSize(4));*/
    }
}
