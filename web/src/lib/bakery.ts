export const bakery = {
  name: "Nete Doces e Salgados",
  whatsapp: "5513988164257",
  phone: "(13) 98816-4257",
  instagram: "netedocesesalgadosgja",
  address: "Rua Araguaia, Sítio Pae Cará — VC",
  opening: "9h30",
  founded: 2016,
  anniversaryMonth: 9,
};

export const instagramUrl = `https://www.instagram.com/${bakery.instagram}/`;
export const whatsappUrl = (message = "Olá, Nete! Gostaria de saber mais sobre os doces e salgados.") =>
  `https://wa.me/${bakery.whatsapp}?text=${encodeURIComponent(message)}`;
export function yearsOfHistory(date: Date) {
  return date.getFullYear() - bakery.founded - (date.getMonth() < bakery.anniversaryMonth ? 1 : 0);
}