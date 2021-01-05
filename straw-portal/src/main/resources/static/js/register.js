let registerApp = new Vue({
    el:'#registerApp',
    methods:{
        register:function (){
           // alert("准备提交注册!");
            $.ajax({
                url:'/portal/users/reg/student',
                data:$('#form-reg').serialize(),
                type:'post',
                success:function (r) {
                    if(r.state==2000){
                        alert('注册成功!');
                    }else {
                        alert(r.message)
                    }
                }
            })
        }
    }
});