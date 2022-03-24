package jisungsoft.com.cmm.service;

public interface SummernoteDataService {

    public Summernote summernoteFileSave(Summernote summernote) throws Exception;

    public Summernote summernoteTempFileSave(Summernote summernote) throws Exception;

    public void summernoteFileView(Summernote summernote) throws Exception;

    public void summernoteTempFileDelete(Summernote summernote) throws Exception;
}
