<template>
  <div class="footer">
    <router-link class="home" to="/"
      ><img src="@/assets/car.svg" class="orange" alt="" width="60px" />
      <h1>Car Store</h1></router-link
    >
    <div class="footer-content">
      <div class="info">
        <div class="other">
          <h3>Правила автосалона</h3>
          <h3>Правовая информация</h3>
        </div>
        <div>
          <h3>Мы в соцсетях</h3>
          <div class="socials">
            <p>YouTube</p>
            <p>Telegram</p>
            <p>ВКонтакте</p>
            <p>WhatsApp</p>
          </div>
        </div>
        <div class="copyright">
          <p>Powered by Vue 3 & SpringBoot</p>
          <p>AMTeam Все праав защищены © 2024</p>
        </div>
      </div>
      <div class="map">
        <h3>Мы на карте</h3>
        <iframe
          src="https://yandex.ru/map-widget/v1/?um=constructor%3Aa1e3a1bdb28f88db5d0432dd68ddf1e1e0a839155bf37df8dc50ad2e1a008b6c&amp;source=constructor"
          width="100%"
          height="200"
          frameborder="0"
        ></iframe>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.footer {
  display: flex;
  flex-direction: column;
  font-family: Montserrat;
  padding-bottom: 70px;
  .footer-content {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .orange {
    filter: invert(77%) sepia(23%) saturate(5926%) hue-rotate(334deg)
      brightness(99%) contrast(104%);
  }
  .home {
    display: flex;
    flex-direction: row;
    align-items: center;
    text-decoration: none;
    padding-bottom: 10px;
    h1 {
      font-size: 24px;
      margin-left: 20px;
      color: white;
    }
  }
  .socials {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    p {
      font-size: 16px;
      font-weight: 600;
      color: #ff9131;
    }
  }
  .other {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .info {
    width: 35%;
    h3 {
      color: #ffffff;
      font-size: 16px;
    }
    .copyright {
      color: #909090;
      font-size: 14px;
      font-weight: 600;
      margin-top: 30px;
    }
  }
  .map {
    width: 50%;
    h3 {
      color: #ffffff;
      font-size: 16px;
    }
  }
}
</style>
