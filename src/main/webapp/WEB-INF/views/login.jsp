<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title> 金证股份 </title>
    <meta name="generator" content="editplus" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="/js/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <h1>你好， 金证！</h1>
    <div>
        账  号：<input name="userName" type="text"/><br />
        密  码：<input name="password" type="password"/><br />
        <span id="tipSpan"></span><br />
        <button onclick="handleSubmit()">提交</button>
    </div>
</body>
</html>
<script type="text/javascript">
    function handleSubmit() {
        var param = {
            userName: $("input[name='userName']").val(),
            password: $("input[name='password']").val()};
        $.ajax({
            url: '/_login/_login',
            data: param,
            type: 'POST',
            success: function (data) {
                debugger;
                if (data.code == 200) {
                    window.location.href = '/page/newCentury/newTest';
                }
                if (data.code == 0) {
                    $('#tipSpan').text(data.msg);
                }
            }
        })
    }
</script>
