<template>
  <div id="loginDiv">
    <img src="../assets/logo.jpg">
    <Form ref="formInline" :model="form" :rules="rule">
      <FormItem prop="user">
        <Input size="large" type="text" v-model="form.name" placeholder="Username">
        <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem prop="password">
        <Input size="large" type="password" v-model="form.password" placeholder="Password">
        <Icon type="ios-lock-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button long type="primary" @click="handleSubmit()">Login</Button>
      </FormItem>
      <span class="clickFont">没有账号？<router-link to="/sign">注册</router-link></span>
    </Form>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        ok:false,
        form: {
          name: '',
          password: ''
        },
        rule: {
          user: [
              {  message: 'Please fill in the user name',
                trigger: 'blur'
              }
          ],
          password: [
            {
              required: true,
              message: 'Please fill in the password.',
              trigger: 'blur'
            },
            { type: 'string',
              min: 6,
              message: 'The password length cannot be less than 6 bits',
              trigger: 'blur'
            }
          ]
        }
      }
    },
    methods: {
      handleSubmit() {
        this.$axios.post('/api/login/verify',{
          account:this.form.name,
          password:this.form.password,
          admin:false
        }).then((response) => {
          if (response.data == true){
            this.$Message.success('登录成功!');
            this.$router.push('index');
          }else{
            this.$Message.error("用户名或密码错误");
          }
        });
      },
    },
    mounted(){
      const marginTop = window.screen.height * 0.15;
      $("#loginDiv").css("margin-top",marginTop);
    }
  }
</script>

<style scope>
  #loginDiv{
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
