package com.example.smartjob.adapter.input.web;

import com.example.smartjob.adapter.model.response.GetUserInformationRestResponse;
import com.example.smartjob.domain.port.input.GetUserInfoUseCase;
import com.example.smartjob.entity.UserInfo;
import io.reactivex.rxjava3.core.Single;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInfoGetControllerTest {

  private UserInfoGetController userInfoGetController;

  @Mock
  private GetUserInfoUseCase getUserInfoUseCase;

  @Mock
  private Function<UserInfo, GetUserInformationRestResponse> getUserInformationRestResponseConverter;


  @Before
  public void before() {
    userInfoGetController = new UserInfoGetController(getUserInfoUseCase, getUserInformationRestResponseConverter);
  }



  @Test
  public void getHeroesTest() {
    when(getUserInfoUseCase.getUserInfo(any(), any())).thenReturn(Single.just(UserInfo.builder().build()));
    when(getUserInformationRestResponseConverter.apply(any())).thenReturn(GetUserInformationRestResponse.builder().build());

    userInfoGetController.getUserInformation("mail@mail.com", "password01")
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

}
