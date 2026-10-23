(() => {
  "use strict";

  const whatsappNumber = "5513988164257";
  const instagramUrl = "https://www.instagram.com/netedocesesalgadosgja/";
  const defaultMessage = "Olá, Nete! Gostaria de saber mais sobre os doces e salgados.";

  document.querySelectorAll("a[data-whatsapp]").forEach((link) => {
    const message = link.dataset.whatsapp || defaultMessage;
    link.href = `https://wa.me/${whatsappNumber}?text=${encodeURIComponent(message)}`;
    link.target = "_blank";
    link.rel = "noopener noreferrer";
  });

  document.querySelectorAll("a[data-instagram]").forEach((link) => {
    link.href = instagramUrl;
    link.target = "_blank";
    link.rel = "noopener noreferrer";
  });

  const menuButton = document.querySelector(".mobile-menu-button");
  const mobileNav = document.querySelector("#mobile-nav");

  function setMenuOpen(open) {
    menuButton.setAttribute("aria-expanded", String(open));
    menuButton.setAttribute("aria-label", open ? "Fechar menu" : "Abrir menu");
    menuButton.innerHTML = `<svg class="icon"><use href="#${open ? "i-close" : "i-menu"}"></use></svg>`;
    mobileNav.hidden = !open;
  }

  menuButton.addEventListener("click", () => {
    setMenuOpen(menuButton.getAttribute("aria-expanded") !== "true");
  });
  mobileNav.querySelectorAll('a[href^="#"]').forEach((link) => {
    link.addEventListener("click", () => setMenuOpen(false));
  });
  document.addEventListener("keydown", (event) => {
    if (event.key === "Escape" && menuButton.getAttribute("aria-expanded") === "true") {
      setMenuOpen(false);
      menuButton.focus();
    }
  });

  const filters = [...document.querySelectorAll("[data-filter]")];
  const products = [...document.querySelectorAll(".product-card")];

  function filterProducts(category) {
    products.forEach((product, index) => {
      const matchesCategory = category === "Todos"
        ? index < 4
        : product.dataset.category === category;
      product.hidden = !matchesCategory;
    });

    filters.forEach((filter) => {
      const isActive = filter.dataset.filter === category;
      filter.classList.toggle("is-active", isActive);
      filter.setAttribute("aria-pressed", String(isActive));
    });
  }

  filters.forEach((filter) => {
    filter.addEventListener("click", () => filterProducts(filter.dataset.filter));
  });
  filterProducts("Todos");
})();
