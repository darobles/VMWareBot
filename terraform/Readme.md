## Installing

#### Install terraform in linux
<pre><code>
sudo yum install -y yum-utils
</code></pre>
<pre><code>
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo
</code></pre>
<pre><code>
sudo yum -y install terraform
</code></pre>
<pre><code>
terraform -v
</code></pre>

#### Installing ovftool
Download ovftool from https://customerconnect.vmware.com/downloads/get-download?downloadGroup=OVFTOOL443
<pre><code>
sudo ./VMware-ovftool-4.4.3-18663434-lin.x86_64.bundle --extract ovftool && cd ovftool
</code></pre>
<pre><code>
./VMware-ovftool-4.4.3-18663434-lin.x86_64.bundle
</code></pre>


### Testing
Run the script
<pre><code>
terraform init
terraform plan
terraform apply
</code></pre>


