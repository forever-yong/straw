 let  questionDetailApp =new Vue({
     el:'#questionDetailApp',
     data:{
         question:{
             title:'测试问题的标题',
             content:'测试问题的正文',
             hits:999,
             userNickName:'咸蛋超人',
             gmtCreateText:'12分钟前'
         },
         answers:[
             {
                 id:2,
                 content:'模拟答案1',
                 userId:12,
                 userNickName: '赛文',
                 isAccepted:0,
                 gmtCreateText:'3分钟前',
                 comments:[
                     {userNickName:'评论员1号',content:'模拟评论1'},
                     {userNickName:'评论员2号',content:'模拟评论2'},
                     {userNickName:'评论员3号',content:'模拟评论3'},
                     {userNickName:'评论员4号',content:'模拟评论4'},
                     {userNickName:'评论员5号',content:'模拟评论5'},
                 ]
             },
             {
                 id:3,
                 content:'模拟答案2',
                 userId:12,
                 userNickName: '迪迦',
                 isAccepted:0,
                 gmtCreateText:'6分钟前',
                 comments:[]
             },
             {
                 id:4,
                 content:'模拟答案3',
                 userId:12,
                 userNickName: '泰罗',
                 isAccepted:0,
                 gmtCreateText:'10分钟前',
                 comments:[]
             },
         ]
     },
     methods:{
         loadQuestionDetail:function () {
             //alert('准备加载问题详情......');
             let id=location.search.substring(1);
             if(id==""||isNaN(id)){
                 alert('错误:缺少必要的参数!');
                 location.href="/";
                 return ;
             }
             $.ajax({
                 url:'/portal/questions/'+id,
                 success:function (r) {
                     if(r.state==2000){
                         let question =r.data;
                         question.gmtCreateText=getTimeText(question.gmtCreate);
                         questionDetailApp.question=question;
                     }else {
                         alert(r.message);
                         location.href='/';
                     }
                 }
             });
         },
         postAnswer:function () {
             //alert('准备发表答案......');
             $.ajax({
                 url:'/portal/answers/post',
                 data:{
                     questionId:location.search.substring(1),
                     content: $('#summernote').val()
                 },
                 type:'post',
                 success:function (r) {
                     if(r.state==2000){
                         alert('发表答案成功!');
                         questionDetailApp.loadAnswers();
                         location.href='#answers';
                         $('#summernote').summernote('reset');
                     }else {
                         alert(r.messages);
                         location.href="/";
                     }
                 }
             });
         },
         loadAnswers:function () {
             $.ajax({
                 url:'/portal/answers',
                 data:'questionId='+location.search.substring(1),
                 success:function (r) {
                     let list =r.data;
                     for (let i = 0; i < list.length; i++) {
                         list[i].gmtCreateText=getTimeText(list[i].gmtCreate);
                     }
                     questionDetailApp.answers=list;
                 }
             });
         },
         postComment:function (answerId) {
             $.ajax({
                url:'/portal/comments/post',
                data:{
                    answerId:answerId,
                    content:$('#commentContent'+answerId).val()
                },
                 type:'post',
                 success:function (r) {
                     if(r.state==2000){
                         alert('发表评论成功!');
                     }else {
                         alert(r.message);
                     }
                     questionDetailApp.loadAnswers();
                 }
             });
         }
     },
     created:function (){
         this.loadQuestionDetail();
         this.loadAnswers();
     }
 });