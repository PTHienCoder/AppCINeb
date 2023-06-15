package com.example.app_project.Service;

public class APIService {

    private static String IP_ADRESS = "http://192.168.63.182:8000/";


    public static String IPIMAGE_POST =IP_ADRESS + "uploadproject/";
    public static String IPIMAGE_USER = IP_ADRESS + "uploads/profile/";
    public static String IMAGE_STORE =  IP_ADRESS + "uploads/store/";

    public static String IPIMAGE_PRODUCT = IP_ADRESS + "uploadproduct/";
    public static String CARTEGORY =  IP_ADRESS + "uploads/category/";

    public static  String Donvi = ".000đ";
    private static String base_url = IP_ADRESS +"api/";




    public static RetrofitInterface getService(){
        return APIRetrofitClient.getClient(base_url).create(RetrofitInterface.class);

    }


}
