package com.example.smartjob.adapter.input.web;


import com.example.smartjob.adapter.model.request.UserInformationRestRequest;
import com.example.smartjob.adapter.model.response.UserInformationRestResponse;
import com.example.smartjob.domain.port.input.PostUserInfoUseCase;
import com.example.smartjob.entity.UserInfo;
import com.example.smartjob.entity.response.UserInfoResponse;
import io.reactivex.rxjava3.core.Single;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.DataBinder;

import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInfoPostControllerTest {

  private UserInfoPostController userInfoPostController;

  @Mock
  private PostUserInfoUseCase postUserInfoUseCase;

  @Mock
  private Function<UserInformationRestRequest, UserInfo> userInfoRequestConverter;

  @Mock
  private Function<UserInfoResponse, UserInformationRestResponse> userInformationRestResponseConverter;


  @Before
  public void before() {
    userInfoPostController = new UserInfoPostController(postUserInfoUseCase, userInfoRequestConverter,
            userInformationRestResponseConverter);
  }



  @Test
  public void getHeroesTest() {
    when(userInfoRequestConverter.apply(any())).thenReturn(UserInfo.builder().build());
    when(postUserInfoUseCase.saveUserInformation(any())).thenReturn(Single.just(UserInfoResponse.builder().build()));
    when(userInformationRestResponseConverter.apply(any())).thenReturn(UserInformationRestResponse.builder().build());

    userInfoPostController.saveUserInformation(new UserInformationRestRequest())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

}
