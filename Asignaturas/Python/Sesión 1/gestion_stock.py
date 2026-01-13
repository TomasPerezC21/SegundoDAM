class Producto:
    def __init__(self, referencia, nombre, precio, stock):
        self.referencia = referencia
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def vender(self, cantidad):
        # Lógica de venta y control de errores 
        if cantidad <= self.stock:
            self.stock -= cantidad
            print(f"Venta exitosa: Se han vendido {cantidad} unidades de {self.nombre}.")
        else:
            # Mensaje de error exacto solicitado 
            print(f"[ERROR] Stock insuficiente para la referencia {self.referencia}")

    def reponer(self, cantidad):
        # Lógica de reposición 
        self.stock += cantidad
        print(f"Reposición exitosa. Nuevo stock de {self.nombre}: {self.stock}")


