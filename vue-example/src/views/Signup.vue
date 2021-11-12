<template>
  <div>
    <h2>Sign Up</h2>
    <input placeholder="Enter your ID" v-model="uid" />
    <input placeholder="Enter your EMAIL" v-model="email" />
    <input placeholder="Enter your password" v-model="password" />
    <button type="button" @click="postRegist">Regist</button>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      uid: "",
      email: "",
      password: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async postRegist() {
      try {
        const data = {
          username: this.uid,
          password: this.password,
          email: this.email,
          role: ["user"],
        };
        const response = await axios.post(
          "http://localhost:8080/api/auth/signup",
          data
        );
        console.log(response);
        alert("Success");
        this.$router.push({
          path: "/signin",
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
