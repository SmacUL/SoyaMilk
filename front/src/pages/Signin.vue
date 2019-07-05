<template>
  <div id="signDiv">
    <img src="../assets/logo.jpg">
    <Form ref="formInline" :model="form" :rules="rule">
      <FormItem prop="user">
        <Input size="large" type="text" v-model="form.user" placeholder="Username">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem prop="password">
        <Input size="large" type="password" v-model="form.password" placeholder="Password">
        <Icon type="ios-lock-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem prop="password">
        <Input size="large" type="password" v-model="form.clickPassword" placeholder="Password">
        <Icon type="ios-lock-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button long type="primary" @click="handleSubmit('form')">Signin</Button>
      </FormItem>
       <span class="clickFont">已有账号？<router-link to="/login">登录</router-link></span>
    </Form>
  </div>
</template>

<script>
    export default {
      data () {
          const validatePassCheck = function (rule,value,callback) {
          if (value === ''){
            return callback(new Error('请再次输入密码'));
          } else if (value !== this.form.password) {
            return callback(new Error('两次密码不一致'));
          } else {
            callback();
          }
        };

        return {
          form: {
            user: '',
            password: '',
            clickPassword:'',
          },
          rule: {
            user: [
              { message: 'Please fill in the user name', trigger: 'blur' }
            ],
            password: [
              { required: true, message: 'Please fill in the password.', trigger: 'blur' },
              { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
            ],
            clickPassword:[
              { validator: validatePassCheck,required: true, message: 'Please fill in the clickPassword.', trigger: 'blur'},
              { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
            ]
          }
        }
      },
      methods: {
        handleSubmit() {
          if (this.form.password == this.form.clickPassword){
            this.$axios.post('/api/signUp/verify',{
              account:this.form.user,
              password:this.form.password,
              admin:false
            }).then((response) => {
              if (response.data == true){
                this.$Message.success('注册成功!');
                this.$router.push('Login');
              }else{
                this.$Message.error("该用户名已被注册");
              }
            });
          }else {
            this.$Message.error("两次输入密码不一致");
          }
        }
      },
      watch:{
      },
      created(){
      },
      mounted(){
        const marginTop = window.screen.height * 0.15;
        $("#signDiv").css("margin-top",marginTop);
      }
    }
</script>

<style scoped>
  #signDiv{
    width: 250px;
    height: 300px;
    margin: 0 auto;
    text-align: center;
  }
  .clickFont{
    font-size: 16px;
  }
  img{
    width: 250px;
    margin-bottom: 50px;
  }
</style>
