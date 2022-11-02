function nullCheck(data, dest, kind) {
    if (data == null || data == "") {
        $(dest).text(kind+"는 필수입니다.");
        return false;
    } else {
        //ajax로 보내는 아이디 중복체크랑 메세지가 두번 나와 일단 주석 처리
       // $(dest).text("사용가능한 "+kind+"입니다.");
       $(dest).text("");
        return true;
    }
}

function equals(data, checkDate, dest) {
    if(data == checkDate) {
        $(dest).text("일치합니다.");
        return true;
    } else {
        $(dest).text("다시 검증해주세요.");
        return false;
    }
}