<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/khach-hang/update/${ma}" method="post" modelAttribute="khDetail">
    <div>
        <label>Mã khách</label>
        <form:input path="ma" readonly="true"/>
    </div>
    <div>
        <label>Tên khách</label>
        <form:input path="ten"/>
        <form:errors path="ten" cssStyle="color: red"/>
    </div>
    <div>
        <label>Loại khách</label>
        <form:select path="loai" id="loai">
            <form:option value="">--Chọn loại--</form:option>
            <form:option value="Vip">Vip</form:option>
            <form:option value="Thường">Thường</form:option>
        </form:select>
        <form:errors path="loai" cssStyle="color: red"/>
    </div>
    <div>
        <label>Giới tính</label>
        <form:radiobutton path="gioiTinh" value="false"/>Nam
        <form:radiobutton path="gioiTinh" value="true"/>Nữ
        <form:errors path="gioiTinh" cssStyle="color: red"/>
    </div>
    <div>
        <label>Tuổi</label>
        <form:input path="tuoi" id="tuoi"/>
        <form:errors path="tuoi" cssStyle="color: red"/>
    </div>
    <div>
        <button type="submit">Update</button>
    </div>
</form:form>
</body>
</html>