//package com.leave.Martind;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//
//@Controller
//@RequestMapping(value = "/report")
//public class ReportFormController extends BaseController {
//
//    @Resource(name = "reportService")
//    private ReportManager reportService;
//
//    /**
//     * 导出报表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/export")
//    @ResponseBody
//    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //获取数据
//        List<PageData> list = reportService.bookList(page);
//
//        //excel标题
//        String[] title = {"名称", "性别", "年龄", "学校", "班级"};
//
//        //excel文件名
//        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";
//
//        //sheet名
//        String sheetName = "学生信息表";
//
//        for (int i = 0; i < list.size(); i++) {
//            content[i] = new String[title.length];
//            PageData obj = list.get(i);
//            content[i][0] = obj.get("stuName").tostring();
//            content[i][1] = obj.get("stuSex").tostring();
//            content[i][2] = obj.get("stuAge").tostring();
//            content[i][3] = obj.get("stuSchoolName").tostring();
//            content[i][4] = obj.get("stuClassName").tostring();
//        }
//
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
//
//        try {
//            this.setResponseHeader(response, fileName);
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //发送响应流方法
//    public void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(), "ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}