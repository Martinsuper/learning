package com.leave.controller;

import com.leave.po.Leave;
import com.leave.service.LeaveService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @ClassName LeaveController
 * @Description
 * @Author martind
 * @Date 2018/8/25 16:05
 **/
@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    /**
     * 提交表单到该页面，进行数据库的插入操作
     *
     * @m_temp 是一个临时的功能，提交表单后会跳转到一个查询结果的界面,列出所有查询结果
     *
     * @param leave
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "leave")
    public String addLeave(Leave leave, Model model) throws IOException {
        leaveService.addLeave(leave);
        List<Leave> leaveList = leaveService.queryAll();
        model.addAttribute("queryAll",leaveList);
        leaveService.leaveOutPut(leaveList,"请假人名单.xlsx");
        return "success";
    }

    /**
     * 删除数据库操作
     * 在该操作中先保存数据到*.xlsx中
     *
     * @return 删除成功界面
     * @throws IOException
     */
    @RequestMapping(value = "delete")
    public String deleteLeave() throws IOException {
        List<Leave> leavesList = leaveService.queryAll();
        leaveService.leaveOutPut(leavesList,"deleteBefore.xlsx");
        leaveService.deleteLeave();
        return "deleteSuccess";
    }

    @RequestMapping("/downLeave")
    public ResponseEntity<byte[]> download() throws IOException {
        leaveService.leaveOutPut(leaveService.queryAll(),"LeaveName.xlsx");
        File file = new File("LeaveName.xlsx");
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }

}
