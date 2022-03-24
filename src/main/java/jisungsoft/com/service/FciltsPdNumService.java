package jisungsoft.com.service;

import jisungsoft.com.persistence.dto.mes.FciltsPdNum;

import java.util.List;

public interface FciltsPdNumService {

    public List<FciltsPdNum> getFciltsPdNumAllList(FciltsPdNum fciltsPdNum);

    public List<FciltsPdNum> getFciltsAndPdNumAllList(FciltsPdNum fciltsPdNum);

    public List<FciltsPdNum> getFciltsPdNumList(FciltsPdNum fciltsPdNum);

    public int getFciltsPdNumListCnt(FciltsPdNum fciltsPdNum);

    public FciltsPdNum getFciltsPdNumDetail(FciltsPdNum fciltsPdNum);

    public void addFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception;

    public void addFciltsPdNumList(FciltsPdNum fciltsPdNum) throws Exception;

    public void editFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception;

    public void removeFciltsPdNum(FciltsPdNum fciltsPdNum) throws Exception;
}
