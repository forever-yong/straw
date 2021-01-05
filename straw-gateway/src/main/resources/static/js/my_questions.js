let myQuestionsApp=new Vue({
    el:"#myQuestionsApp",
    data:{
        questions:[
            {
                statusText:'未解决',
                statusClass:'badge-warning',
                title:'问题1的标题',
                content:'问题1的正文',
                tags:[{id:1,name:'java基础'},{id:2,name:'javaOOP'}],
                userNickName:'张三',
                hits:916,
                gmtCreateText:'3小时前'
            },
            {
                statusText:'已回复',
                statusClass:'badge-info',
                title:'问题2的标题',
                content:'问题2的正文',
                tags:[{id:5,name:'Mybatis'},{id:6,name:'MybatisPlus'}],
                userNickName:'张三',
                hits:185,
                gmtCreateText:'11小时前'
            },
            {
                statusText:'已解决',
                statusClass:'badge-success',
                title:'问题3的标题',
                content:'问题3的正文',
                tags:[{id:2,name:'springboot'},{id:7,name:'springMVC'}],
                userNickName:'张三',
                hits:105,
                gmtCreateText:'5小时前'
            },
        ],
        prePage:0,
        nextPage:0,
        navigatepageNums:[],
        currentPageNum:0
    },
    methods:{
        loadMyQuestions:function (page) {
            if(page<1){
                return ;
            }
            $.ajax({
                url:'/api-question/v1/questions/my',
                data:'page='+page,
                success:function (r) {
                    let list =r.data.list;
                    let statusTexts=['未回复','已回复','已解决'];
                    let statusClasses=['badge-warning','badge-info','badge-success'];
                    for (let i = 0; i < list.length; i++) {
                        list[i].statusText = statusTexts[list[i].status];
                        list[i].statusClass = statusClasses[list[i].status];
                        list[i].gmtCreateText = getTimeText(list[i].gmtCreate);
                    }
                    myQuestionsApp.questions=list;
                    myQuestionsApp.prePage=r.data.prePage;
                    myQuestionsApp.nextPage=r.data.nextPage;
                    myQuestionsApp.navigatepageNums=r.data.navigatepageNums;
                    myQuestionsApp.currentPageNum=r.data.pageNum;
                }
            });
        }
    },
    created:function () {
        this.loadMyQuestions(1);
    }
});