#Assembly program that converts from Celsius to Fahrenheit
	
	.text
	.globl main
main:
	li 	$v0, 4
	la 	$a0, prompt
	syscall

	li 	$v0, 5
	syscall

	mul	$t0, $v0, 9
	div	$t0, $t0, 5
	add	$t0, $t0, 32

	li	$v0, 4
	la	$a0, ans1
	syscall
	
	li 	$v0, 1
	move	$a0, $t0
	syscall
	
	li	$v0, 4
	la	$a0, end1
	syscall

	li	$v0, 10
	syscall

	.data
prompt: .asciiz		"Enter a temperature (Celcius): "
ans1: 	.asciiz		"The temperature in Fahrenheit is: "
end1: 	.asciiz		"\n"
