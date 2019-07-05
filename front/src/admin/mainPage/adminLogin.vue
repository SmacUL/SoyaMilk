<template>
  <div id="loginDiv">
    <img src="../../assets/logo.png">
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
        <Button long type="primary" @click="handleSubmit('form')">Login</Button>
      </FormItem>
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
            { required: true, message: 'Please fill in the user name', trigger: 'blur',type:'string' }
          ],
          password: [
            { required: true, message: 'Please fill in the password.', trigger: 'blur' },
            { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      handleSubmit(name) {
        console.log(this.form.name)
        console.log(this.form.password)
        this.$axios.post('/api/login/verify',{
          account:this.form.name,
          password:this.form.password,
          admin:true
        }).then((response) => {
          if (response.data == true){
            this.$Message.success('登录成功!');
            this.$router.push('index');
          }else{
            this.$Message.error("用户名或密码错误");
          }
        });
      },
    }
  }
</script>

<style scoped>
  #loginDiv{
    width: 250px;
    height: 300px;
    margin: 0 auto;
  }
  .clickFont{
    font-size: 16px;
  }
</style>
