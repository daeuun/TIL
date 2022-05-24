using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WebsocketTest
{
    public partial class FrmWs : Form
    {
        public FrmWs()
        {
            InitializeComponent();
        }

        private void btnConnect_Click(object sender, EventArgs e)
        {
            Console.WriteLine("웹소켓 서버를 시작합니다.");
            wsServer ws = new wsServer("127.0.0.1", 8181);
        }
    }
}
