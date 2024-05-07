<template>
  <div class="cart">
    <div class="title">
      <h1 class="cart-title">Корзина</h1>
      <button v-if="this.response[0]" @click="clearCart()" class="clear-btn">
        <i class="fa-regular fa-trash-can"></i> Очистить корзину
      </button>
    </div>
    <router-link to="/login" v-if="!checkAuthorization()" class="warn">
      <h2>Вы не авторизованы! Войдите в аккаунт.</h2>
      <img src="@/assets/to-login.svg" class="orange" alt="" />
    </router-link>
    <router-link
      to="/"
      v-if="!this.response[0] && checkAuthorization()"
      class="warn"
    >
      <h2>Ваша корзина пуста. Перейти к покупкам.</h2>
      <img src="@/assets/cart.svg" class="orange" alt="" />
    </router-link>
    <div v-if="checkAuthorization() && this.response[0]" class="cart-items">
      <div class="orders-list">
        <OrderItem
          v-for="order in this.response"
          :key="order.id"
          :order="order"
        />
      </div>
      <div class="order-area">
        <h2>Ваша корзина</h2>
        <p>
          Количество товаров - {{ this.orderCount }} шт.
          <i class="car-icon fa-solid fa-car"></i>
        </p>
        <h2>
          Сумма заказа:<br />
          {{ this.splitPrice(this.orderAmount) }} &#8381;
        </h2>
        <button @click="makeOrder()" class="make-order">Оформить заказ</button>
      </div>
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";
import OrderItem from "@/components/OrderItem.vue";

export default {
  data() {
    return {
      response: {},
      userId: localStorage.getItem("userId"),
      orderAmount: 0,
      orderCount: 0,
    };
  },
  components: {
    OrderItem,
  },
  methods: {
    updateCart() {
      this.orderAmount = 0;
      this.orderCount = 0;
      const amounts = document.querySelectorAll(".price");
      const counters = document.querySelectorAll(".amount");
      for (const amount of amounts) {
        this.orderAmount += Number(
          amount.innerText.split(" ").slice(0, -1).join("")
        );
      }
      for (const counter of counters) {
        this.orderCount += Number(counter.innerText);
      }
    },
    checkAuthorization() {
      return localStorage.getItem("token");
    },
    splitPrice(price) {
      const reversedArr = String(price).split("").reverse();
      let newString = [];
      for (let i = 0; i < reversedArr.length; i++) {
        newString.push(String(reversedArr[i]));
        if ((i + 1) % 3 === 0) {
          newString.push(" ");
        }
      }
      return newString.reverse().join("");
    },
    clearCart() {
      this.response = {};
      AXIOS.delete(
        "https://car-store-backend-amhi.onrender.com/car-store/user/" + this.userId + "/car",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
    },
    makeOrder() {
      AXIOS.post(
        "https://car-store-backend-amhi.onrender.com/car-store/user/" + this.userId + "/email",
        {},
        {
          headers: {
            Receiver: localStorage.getItem("userEmail"),
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      ).then(() => {
        this.clearCart();
        alert(
          "Заказ оформлен! На вашу почту отправлено письмо с подробной информацией."
        );
        location.reload();
      });
    },
  },
  created() {
    if (localStorage.getItem("token")) {
      AXIOS.get(
        "https://car-store-backend-amhi.onrender.com/car-store/users/" + this.userId + "/cars",
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      )
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          if (localStorage.getItem("token")) {
            alert("Время вашей сессии истекло!");
            localStorage.removeItem("token");
          }
        });
    }
  },
  mounted() {
    setTimeout(() => {
      this.updateCart();
      for (const element of document.querySelectorAll(".button")) {
        element.addEventListener("click", () => {
          this.updateCart();
        });
      }
      for (const element of document.querySelectorAll(".rem-item")) {
        element.addEventListener("click", () => {
          this.updateCart();
        });
      }
    }, 500);
  },
};
</script>

<style lang="scss" scoped>
.cart-items {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .order-area {
    .car-icon {
      color: #ff9131;
    }
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    position: sticky;
    top: 60px;
    width: 25%;
    padding: 20px;
    height: 350px;
    font-family: Montserrat;
    border-radius: 20px;
    transition: 0.1s;
    color: #ffffff;
    background-color: #3a3a3a;
    &:hover {
      box-shadow: 0px 2px 24px 2px #f4f4f415;
    }
    h2 {
      font-size: 24px;
    }
    p {
      font-size: 16px;
    }
    .make-order {
      background-color: #ff9131;
      border: none;
      font-family: Montserrat;
      font-size: 20px;
      width: 100%;
      padding: 20px 0;
      border-radius: 8px;
      font-weight: 600;
      cursor: pointer;
      color: #0e0c0d;
      transition: 0.1s;
      &:hover {
        color: #fff;
        box-shadow: 0px 2px 24px 2px #f4f4f415;
      }
    }
  }
}
.unauthorized {
  h2 {
    color: #ffffff;
    margin: 60px auto 40px;
    font-family: Montserrat;
    font-weight: 600;
    font-size: 24px;
    text-align: center;
  }
  text-decoration: none;
}
.orange {
  filter: invert(77%) sepia(23%) saturate(5926%) hue-rotate(334deg)
    brightness(99%) contrast(104%);
  opacity: 0.7;
}
.warn {
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  width: 45%;
  h2 {
    color: #ffffff;
    margin: 60px auto 40px;
    font-family: Montserrat;
    font-weight: 600;
    font-size: 24px;
    text-align: center;
  }
  text-decoration: none;
  img {
    margin: 0 auto;
    width: 40%;
  }
}
.title {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  .cart-title {
    font-family: Montserrat;
    font-size: 32px;
    font-weight: 800;
    color: #ff9131;
    margin-bottom: 10px;
  }
  .clear-btn {
    padding: 10px 15px;
    background-color: #ffffff;
    border-radius: 8px;
    border: 1px solid #231f20;
    font-size: 15px;
    font-family: Montserrat;
    font-weight: 600;
    cursor: pointer;
    &:active {
      background-color: #f3f3f3;
    }
  }
}
.orders-list {
  display: flex;
  flex-direction: column;
  width: 70%;
  align-items: center;
  margin: 0 0 auto;
  padding-bottom: 40px;
}
</style>
