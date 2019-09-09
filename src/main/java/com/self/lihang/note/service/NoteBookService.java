package com.self.lihang.note.service;

import com.self.lihang.note.bean.NoteBook;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserNoteAndBookModel;
import com.self.lihang.note.pageModel.Page;

import java.util.List;

public interface NoteBookService {

    List<NoteBook> getHotNote(Integer BookId);

    boolean userCreateNotebook(User user,Integer bookId);

    Page getNoteBookByBookId(Integer bookId,Page page);

    Page getNoteBookByuserId(Integer userId, Page page);

    UserNoteAndBookModel getNoteInfo(Integer notebookId);

    boolean userAddNewNoteBook(User user, NoteBook noteBook);

}
