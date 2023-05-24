#
#  See https://www.terraform.io/intro/getting-started/variables.html for more details.
#

#  Change these defaults to fit your needs!
variable "esxi_hostname" {
  default = ""
}

variable "esxi_hostport" {
  default = "22"
}

variable "esxi_hostssl" {
  default = "443"
}

variable "esxi_username" {
  default = "root"
}

variable "esxi_password" { # Unspecified will prompt
  default = ""
}

variable "virtual_network" {
  default = "VM Network"
}

variable "disk_store" {
  default = "datastore1"
}

variable "ovf_file" {
  #  A local file downloaded from https://cloud-images.ubuntu.com -auto-approve
  default = ""
}

variable "vm_hostname" {
  default = ""
}
