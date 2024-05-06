<template>
  <nav>
    <router-link class="home" to="/"
      ><img src="@/assets/car.svg" class="orange" alt="" width="100px" />
      <h1>Car Store</h1></router-link
    >
    <div :key="this.authorized" class="container">
      <router-link class="cart" to="/cart"> Корзина </router-link>
      <router-link v-if="!this.authorized" class="login" to="/login"
        >Войти</router-link
      >
      <router-link v-if="!this.authorized" class="register" to="/register"
        >Регистрация</router-link
      >
      <button v-if="this.authorized" @click="logout()" class="logout">
        Выйти
      </button>
      <router-link to="/user" v-if="this.authorized" class="user"
        >Кабинет</router-link
      >
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      authorized: localStorage.getItem("token"),
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("userId");
      this.authorized = null;
    },
  },
};
</script>

<style lang="scss" scoped>
nav {
  .orange {
    filter: invert(77%) sepia(23%) saturate(5926%) hue-rotate(334deg)
      brightness(99%) contrast(104%);
  }
  h1 {
    font-size: 36px;
    margin-left: 20px;
    color: white;
  }
  padding-top: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  font-family: Montserrat;
  font-weight: 700;
  font-size: 16px;
  .container {
    display: flex;
    width: 35%;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }
  .home {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .cart {
    position: relative;
    padding: 10px 15px;
    background-color: #ff9131;
    border-radius: 8px;
  }
  .login {
    color: #ff9131;
  }
  a {
    text-decoration: none;
    display: block;
    color: white;
  }
  .logout {
    background: transparent;
    border: none;
    color: #696f7a;
    font-family: Montserrat;
    font-weight: 700;
    font-size: 16px;
    cursor: pointer;
  }
}
</style>
