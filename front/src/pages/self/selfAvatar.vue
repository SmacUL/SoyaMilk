<template>
  <div id="avatar">
      <div class="imgDiv" style="float: left">
        <img :src="user.avatarUrl" @click="imgClick">
        <form action="/api/changeAvatar" style="display: none">
            <input id="avatarInput" type="file" @change="uploadImg">
        </form>
      </div>
      <div style="display: inline-block;height: 100%;width: 500px;float: left">
        <div style="width: 100%;padding-top: 30px;padding-left: 50px">
          <span class="name">{{ user.name }}</span>
        </div>
        <div style="padding-left: 50px;width: 100%;height: 30px;padding-top: 10px">
            <p v-if="id != 0" style="display: inline-block;float: left">{{ user.selfStyle }}</p>
            <Input v-else style="display: inline-block;float: left" v-model="user.selfStyle" placeholder="Enter something..." on-blur="changeSelfStyle" />
        </div>
        <div style="padding-left: 50px;width: 100%;height: 30px;padding-top: 10px">
          <Button v-if="id != 0" @click="followIt" :type="follow.type">{{ follow.text }}</Button>
        </div>
      </div>
  </div>
</template>

<script>

    import bus from '../../js/bus';
    export default {
        name: "selfAvatar",
        data(){
          return{
            user: {
              avatarUrl: '../../assets/logo.png',
              name: '',
              selfStyle: ''
            },
            follow: {
                isFollow:false,
                text: '关注TA',
                type: 'success'
            },
            avatar:''
          }
        },
        computed:{
          id(){
            return this.$route.params.userId
          }
        },
        methods:{
            imgClick(){
                if (this.id != 0){     //可以改为查看大图
                  return false;
                }
                document.getElementById("avatarInput").click();

            },
            uploadImg(event){
              let file=event.target.files[0];
              this.avatar = file;
              let formdata = new FormData();
              formdata.append("file",this.avatar);
              this.$axios.post('/api/changeAvatar',formdata, {
                headers:{
                  'Accept':'application/json',
                  'Content-Type':'multipart/form-data'
                }
              }).then((response) => {
                  this.$Message.success('更改头像成功！');
                  this.user.avatarUrl = response.data;
              });
            },
            changeSelfStyle(){
                this.$axios.post('/api/changeSelfStyle',{
                  selfStyle:this.user.selfStyle
                }).then(response =>{
                    if (response.data){
                      this.$Message.success("修改成功！");
                    }
                });
            },
            followIt(){
              this.follow.isFollow = !this.follow.isFollow;

              if (this.follow.isFollow){
                this.$axios.post('/api/follow/'+this.id).then(response=>{
                  if (response.data){
                    this.$Message.success("关注成功!");
                  }
                });
              }else {
                this.$axios.post('/api/unfollow/'+this.id).then(response=>{
                  if (response.data){
                    this.$Message.success("取消关注成功!");
                  }
                });
              }
            },
            loadUser(id){
              this.$axios.post('/api/loadUser/'+id).then(response=>{
                this.user = response.data;
              });
            },
            followOrnot(id){
              this.$axios.post('/api/isFollow/'+id).then(response=>{
                this.follow.isFollow = response.data;
              });
            },

        },
        created(){
            this.loadUser(this.id);
            this.followOrnot(this.id);
            let obj = this;

            bus.$on('RouteUpdate',function (userId) {
              this.$axios.post('/api/loadUser/'+userId).then(response=>{
                console.log(this == obj);
                obj.user = response.data;
              });
              this.$axios.post('/api/isFollow/'+userId).then(response=>{
                obj.follow.isFollow = response.data;
              });
            });
        },
        watch:{
            'follow.isFollow'(){
                if (this.follow.isFollow){
                    this.follow.text = '取消关注';
                    this.follow.type = 'error';
                }else{
                    this.follow.text = '关注TA';
                    this.follow.type = 'success';
                }
            }
        },
    }
</script>

<style scoped>
  #avatar{
    width: 100%;
    margin: 0 auto;
    height: 150px;
    background-color: #FFFFFF;
    margin-top: 10px;
    margin-bottom: 10px;
    padding: 1px;
  }
  .imgDiv{
    width: 110px;
    height: 110px;
    background-color: #5b6270;
    border-radius:90px ;
    margin-top: 30px;
    margin-left: 20px;
    display: inline-block;
  }
  .imgDiv > img{
    width: 100%;
    height: 100%;
    border-radius: 90px;
    cursor: pointer;
  }
  .name{
    font-size: 20px;
  }
</style>
