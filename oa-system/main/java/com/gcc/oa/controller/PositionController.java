package com.gcc.oa.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcc.oa.constant.Constants;
import com.gcc.oa.constant.ExceptionCodeMessage;
import com.gcc.oa.dto.response.ResponseData;
import com.gcc.oa.dto.request.PositionReqDTO;
import com.gcc.oa.dto.response.PositionRespDTO;

import com.gcc.oa.error.position.PositionAddFailedException;
import com.gcc.oa.error.position.PositionUpdateFailedException;
import com.gcc.oa.error.position.UpdatePositionStatusFailedException;
import com.gcc.oa.service.PositionService;
import com.gcc.oa.util.EmptyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 职位表(Position)表控制层
 *
 * @author 小李探花
 * @since 2022-01-18 12:28:30
 */
@Api(tags = "职位控制层")
@RestController
@RequestMapping("pos")
@RequiresPermissions("position-*")
public class PositionController {

    @Resource
    PositionService positionService;

    @ApiOperation("使用分页获取所有的职位信息")
    @GetMapping("position")
    public ResponseData queryPageList(PositionReqDTO positionReqDTO) {
        return positionService.queryPageList(positionReqDTO);
    }

    @ApiOperation("不使用分页获取所有的职位信息")
    @GetMapping("all")
    public ResponseData queryAll() {
        return positionService.queryAll();
    }


    @ApiOperation("修改状态")
    @PutMapping("status/{positionId}")
    public ResponseData updateStatus(@PathVariable("positionId") Integer positionId, @RequestBody PositionReqDTO reqDTO) {
        ResponseData responseData;
        //如果不为空
        if (EmptyUtil.notEmpty(positionId) && EmptyUtil.notEmpty(reqDTO)) {
            try {
                //修改职位状态
                responseData = positionService.updateStatus(reqDTO);
                //如果修改成功,则查询修改后的数据,返回给前端
                PositionRespDTO respDTO = positionService.queryById(reqDTO.getPositionId());
                responseData.putDataValue(Constants.POSITION_KEY_NAME.getName(), respDTO);
            } catch (UpdatePositionStatusFailedException e) {
                e.printStackTrace();
                //失败则返回异常信息
                responseData = ResponseData.error(e.getCode(), e.getMessage());
                responseData.putDataValue(Constants.POSITION_EXCEPTION.getName(), e);
            }
        } else {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.POSITION_UPDATE_FAILED, ExceptionCodeMessage.Message.POSITION_UPDATE_FAILED_MSG);
        }
        return responseData;
    }

    @ApiOperation("新增职位或者修改职位")
    @PostMapping("save")
    public ResponseData addPosition(@RequestParam String position) {
        ResponseData responseData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //需要把字符串序列化为对象
            PositionReqDTO positionReqDTO = objectMapper.readValue(position, PositionReqDTO.class);
            //如果positionId存在,则进行修改
            if (EmptyUtil.notEmpty(positionReqDTO.getPositionId())) {
                positionService.updatePosition(positionReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.POSITION_UPDATE_SUCCESS_MSG);
            } else {
                positionService.addPosition(positionReqDTO);
                responseData = ResponseData.ok(ExceptionCodeMessage.Message.POSITION_ADD_SUCCESS_MSG);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            responseData = ResponseData.error(ExceptionCodeMessage.Code.POSITION_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.POSITION_EXCEPTION.getName(), e);
        } catch (PositionAddFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.POSITION_ADD_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.POSITION_EXCEPTION.getName(), e);
        } catch (PositionUpdateFailedException e) {
            responseData = ResponseData.error(ExceptionCodeMessage.Code.POSITION_UPDATE_FAILED,
                    e.getMessage());
            responseData.putDataValue(Constants.POSITION_EXCEPTION.getName(), e);
        } catch (Exception e) {
            responseData = ResponseData.otherError(ExceptionCodeMessage.Code.OTHER_ERROR, e.getMessage());
        }
        return responseData;
    }
}

