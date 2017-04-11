package util;

import java.util.List;
import java.util.Map;

import model.APPVersionModel;
import model.ActityModel;
import model.BannerModel;
import model.ChongzhiModel;
import model.DepartmentModel;
import model.GoodsModel;
import model.GroupModel;
import model.HFBModel;
import model.HouseModel;
import model.LanchModel;
import model.MessageCountModel;
import model.NewsModel;
import model.QuanModel;
import model.RenzhengModel;
import model.UserModel;
import model.YouhuiquanModel;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by X on 2016/10/1.
 */

public interface ServicesAPI {

 String APPUrl = "http://apioa.sssvip.net/Public/taihangoa/";
 //String APPUrl = "http://192.168.1.106/PhalApi/Public/taihangoa/";

 @GET("?service=APP.getLastAPPVersion")  //获取APP最新版本信息
 Observable<HttpResult<APPVersionModel>> APPGetLastAPPVersion();

 @GET("?service=System.GetDepartmentList")  //获取部门列表
 Observable<HttpResult<List<DepartmentModel>>> SystemGetDepartmentList();

 @GET("?service=APP.GetAPPLanuch")  //获取APP启动图
 Observable<HttpResult<LanchModel>> APPGetAPPLanuch();

 @Multipart
 @POST("?service=User.headEdit")//上传用户头像
 Observable<HttpResult<Object>> userHeadEdit(@PartMap Map<String , RequestBody> params);

 @POST("?service=User.userEdit")  //完善个人信息
 Observable<HttpResult<Object>> userUserEdit(
         @Query("username") String username,
         @Query("nickname") String nickname,
         @Query("sex") String sex,
         @Query("truename") String truename,
         @Query("birthday") String birthday,
         @Query("address") String address,
         @Query("aihao") String aihao,
         @Query("qianming") String qianming
 );

 @GET("?service=User.getOrNickname")  //检测昵称是否重复
 Observable<HttpResult<Object>> userGetOrNickname(@Query("nickname") String nickname);


 @GET("?service=User.Checktoken")  //检测昵称是否重复
 Observable<HttpResult<Object>> UserChecktoken(@Query("uid") String uid,
                                               @Query("mobile") String mobile,
                                               @Query("token") String token);

}


