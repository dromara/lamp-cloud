package com.github.zuihou.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.github.zuihou.authority.dto.auth.UserExcelVO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.model.RemoteData;
import lombok.SneakyThrows;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPoiTest {

    public static final String OUTPUT_PATH = "/Users/tangyh/Downloads/testPoi/";

    private static List<User> USER_LIST = new ArrayList<>();
    private static List<UserExcelVO> USER_EXCEL_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < 2; i++) {
            User user = User.builder()
                    .orgId(new RemoteData<>(Long.valueOf(i), Org.builder().label("org" + i).build()))
                    .stationId(new RemoteData<>(Long.valueOf(i), "stataiin" + i))
                    .account("账号" + i)
                    .name("名字" + i)
                    .mobile("15218869970")
                    .sex(i % 2 == 0 ? Sex.M : Sex.M)
                    .nation(new RemoteData<>("HELLO" + i))
                    .education(new RemoteData<>("xueli" + i, "本科"))
                    .workDescribe("工作描述工作描述工作描述工作描述工作描述工作描述工作描述." + i)
                    .status(true)
                    .createTime(LocalDateTime.now())
                    .lastLoginTime(LocalDateTime.now())
                    .build();
            if (i == 0) {
                user.setSex(null);
            }
            if (i == 1) {
                user.setSex(Sex.N);
            }
            USER_LIST.add(user);
            UserExcelVO userEX = UserExcelVO.builder()
                    .account("账号" + i)
                    .name("名字" + i)
                    .mobile("15218869970")
                    .sex(i % 2 == 0 ? Sex.M : Sex.M)
                    .nation(i % 2 == 0 ? "汉族" : "壮族")
                    .org(12L)
                    .education(i % 2 == 0 ? "本科" : "大专")
//                    .nation(new RemoteData<>("HELLO" + i, "汉族" + i))
                    .workDescribe("工作描述工作描述工作描述工作描述工作描述工作描述工作描述." + i)
                    .status(true)
                    .lastLoginTime(LocalDateTime.now())
                    .build();
            if (i == 0) {
                userEX.setSex(null);
            }
            if (i == 1) {
                userEX.setSex(Sex.N);
            }
            USER_EXCEL_LIST.add(userEX);
        }
    }

    @Test
    public void testImport() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setVerifyHandler(new ExcelUserVerifyHandlerImpl());
        params.setDictHandler(new ExcelDictHandlerImpl());

        ExcelImportResult<UserExcelVO> result = ExcelImportUtil.importExcelMore(
                new File(OUTPUT_PATH + "User2.xlsx"),
                UserExcelVO.class, params);

        FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "ExcelVerifyTest.user2.xlsx");
        result.getFailWorkbook().write(fos);
        fos.close();
        for (int i = 0; i < result.getList().size(); i++) {
            System.out.println(ReflectionToStringBuilder.toString(result.getList().get(i)));
        }

        System.out.println(result.getList().size());

    }

    @Test
    public void testImportMap() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);

        List<Map<String, Object>> list = ExcelImportUtil.importExcel(new File(OUTPUT_PATH + "User2.xlsx"), Map.class, params);

        ExcelImportResult<UserExcelVO> result = ExcelImportUtil.importExcelMore(
                new File(OUTPUT_PATH + "User2.xlsx"),
                UserExcelVO.class, params);


        System.out.println(list.size());
        System.out.println(result.getList().size());

    }

    @Test
    @SneakyThrows
    public void testExport() {
        ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, UserExcelVO.class, USER_EXCEL_LIST);
        File savefile = new File(OUTPUT_PATH);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "User2.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
