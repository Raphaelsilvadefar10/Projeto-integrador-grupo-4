import { describe, expect, it } from "vitest";
import { bakery, yearsOfHistory, instagramUrl } from "../lib/bakery";

describe("Informações da Nete", () => {
  it("completa 10 anos em outubro de 2026", () => {
    expect(yearsOfHistory(new Date(2026, 9, 8))).toBe(10);
    expect(yearsOfHistory(new Date(2026, 8, 8))).toBe(9);
  });
  it("preserva o horário informado de 9h30", () => {
    expect(bakery.opening).toBe("9h30");
  });
  it("usa o perfil informado pelo proprietário", () => {
    expect(instagramUrl).toBe("https://www.instagram.com/netedocesesalgadosgja/");
  });
});