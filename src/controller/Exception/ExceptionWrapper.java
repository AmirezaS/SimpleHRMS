package controller.Exception;

import java.sql.SQLDataException;

public class ExceptionWrapper {

    public static String getExceptionMsg(Exception e){
        if (e instanceof SQLDataException){
            return "DATABASE MOSKEL DARAD";
        }else if (e instanceof NumberFormatException){
            return "LOTFAN DOROST VARED KONID";
        }else if (e instanceof NullPointerException){
            return "LOTFAN KHALI NAZARID";
        }else {
            return "KHATA DARAD" + e.getMessage();
        }
    }
}
